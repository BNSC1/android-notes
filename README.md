# Android

## Components

Activity: entry point for user interaction e.g. track what is on the screen
- Activity with transparent background: apply a theme which its `android:windowIsTranslucent` is set to true and its `android:windowBackground` is set to transparent

`Service`: runs in the background to perform long-running tasks, such as playing music, downloading data, or syncing data with a server. A `Service` does not provide a user interface, and it can run even when the app that started it is no longer in the foreground. There are two types of `Services` in Android:
- Started `Service`: A Started `Service` is a `Service` that is started by an `Activity` or other component using the `startService()` method. Once started, a Started `Service` continues to run until it completes its work or is stopped by calling `stopService()` or `stopSelf()`.
- Bound `Service`: A Bound `Service` is a `Service` that is bound to an `Activity` or other component using the `bindService()` method. A Bound `Service` provides a client-server interface that allows the client to interact with the `Service` and perform operations on it. A Bound `Service` continues to run as long as there is at least one client bound to it.

Foreground `Service`: it is visible to the user as a notification, and unlike a normal `Service`, it cannot be stopped by Android even if low on resources, requires the `START_STICKY` flag to restart if terminated

`IntentService`: a subclass of `Service` that provides a simple way to perform long-running tasks in the background. An `IntentService` runs in a separate thread, so it does not block the main UI thread, and it automatically stops itself when it has completed its work.

`BroadcastReceiver`: a component that allows your app to receive and respond to system-wide broadcast events or custom broadcast events that are sent from other apps or from the system itself.

`LocalBroadcastManager`: a helper class that allows your app to send and receive broadcast messages within the same app. A `LocalBroadcastManager` is a subclass of the abstract class `BroadcastReceiver`, and it is used to create a private communication channel for your app that is not visible to other apps on the device.

There are 2 types of broadcasts:
- Normal Broadcasts: A normal broadcast is a broadcast in which the system delivers the broadcast to all interested receivers without any particular order. Normal broadcasts are asynchronous, so the broadcast is sent to all receivers at the same time and the order in which they receive it is not guaranteed. Normal broadcasts are useful when you want to notify multiple components of an event, such as when the battery is low or when an SMS message is received.
- Ordered Broadcasts: An ordered broadcast is a broadcast in which the system sends the broadcast to one receiver at a time, in a specific order. The first receiver to receive the broadcast can abort the broadcast, prevent other receivers from receiving it, or modify the data being broadcasted. Ordered broadcasts are useful when you want to ensure that the broadcast is processed in a specific order, or when you need to modify or filter the data being broadcasted.

There are also 2 categories of broadcasts:
- System Broadcasts: System broadcasts are predefined by the Android system and are sent by the system when certain events occur, such as when the battery is low or when the device is connected to a charger. System broadcasts cannot be modified or cancelled by the app that receives them.
- Custom Broadcasts: Custom broadcasts are broadcasts that are defined by the app itself, and are sent using the `sendBroadcast()` or `sendOrderedBroadcast()` methods. Custom broadcasts can be used to notify other components within the same app or in other apps of an event or to communicate data between components.


Content providers: provide an API to share data with other apps, sharing can be done by URI

Intent: an asynchronous message which activates Activities, Services, and Broadcast receivers

- Explicit Intent: specify which component will handle this action

- Implicit Intent: specify what action to be handled by components

- Sticky Intent: still exists after broadcasting, allowing other components to collect data

`PendingIntent`: a token that you can use to give another application permission to perform an operation on your app's behalf. A `PendingIntent` is typically created by the current app and passed to another app that you want to grant permission to perform an operation.

Module: a collection of source files and build settings to divide the project for discrete functionality

`build.gradle`: build configuration that applies to all modules

`AndroidManifest.xml`: declare existing components, API level, required permissions, required hardware features

- `manifestPlaceholders`: creates "variables" to be used in `AndroidManifest.xml`

- `<intent-filter>`: a component that is used to specify the types of Intent messages that a component can receive or respond to. An `IntentFilter` is used to filter the incoming Intents and determine which component should handle the Intent.

- launchMode:

    - standard: default, will create a new activity regardless if it is in the task stack

    - singleTop: if the target activity is already on top, use it, otherwise, create a new one

    - singleTask: if the target activity is already in the task stack, use it and pop activities above it, otherwise create a new one

    - singleInstance: create a new activity in a new task

Context: an abstract class that provides access to application-specific resources and services, such as the system services, preferences, and assets. It represents the current state of the app and provides access to various resources and information about the app environment. Its usages are:
- Creating views
- Accessing resources
- Starting activities
- Accessing system services

`applicationContext`: for reference by singleton class e.g. room, datastore

`activityContext`: for reference by UI operations e.g. toast, dialog

Application: contains all other components such as activities and services. It is instantiated before any other class when the process for your application/package is created.

Fragment: has its layout and its behavior with its lifecycle callbacks, can add or remove fragments in the activity, can combine multiple fragments in an activity, can be reused in multiple activities, the lifecycle is closely related to the lifecycle of its host activity.  Fragment was introduced with Android 3.0, which also introduced tablet support.

When to use a `Fragment` over an `Activity`:
- reuse a UI component across different activities.
- create a multi-pane user interface, such as a tablet layout.
- handle different device configurations, such as screen size or orientation changes.
- separate your code into smaller, more manageable pieces.
- update a portion of the UI without affecting the rest of the screen.

Ways to communicate between two fragments:
- Shared ViewModel: create a shared ViewModel between two fragments that belong to the same activity. The ViewModel can hold the shared data or state and both fragments can access and modify it as needed.
- Callback interface: define a callback interface in the first fragment and implement it in the parent activity. Then, you can pass the instance of the implemented interface to the second fragment and call the interface method from the second fragment to communicate back to the first fragment.
- `FragmentManager`: The `FragmentManager` can be used to find a fragment by its tag or ID and then call a public method on that fragment to communicate with it.
- shared data storage: You can use a shared data storage, such as a database or a shared preferences file, to store the data that needs to be shared between the two fragments. Both fragments can access the data storage and read or write the data as needed.

- Fragment replace: removes existing fragment and adds new fragment

- Fragment add: retains existing fragment and adds new fragment

- Retained Fragment: By default, Fragments are destroyed and recreated along with their parent Activity’s when a configuration change occurs. Calling `setRetainInstance(true)` bypasses this cycle, retaining the current instance of the fragment when the activity is recreated.

- `addToBackStack()`: replace transaction is saved to back stack so the user can bring back the previous fragment with the back button

- When the user hits the system Back button, going from B back to A, the reverse happens: the entering destination A will have the popEnterAnim applied to it and the exiting destination B will have the popExitAnim applied to it.

- Using only the default Fragment constructor is recommended because Android calls a no-argument constructor after a configuration change, and it is not aware of others

`jni/`: native code using the Java Native Interface (JNI)

`gen/`: contains the Java files generated by Android Studio

`assets/`: contains the file that should be compiled into an .apk file as-is. Can navigate this directory in the same way as a typical file system using URIs and read files as a stream of bytes using the AssetManager. A good location for textures and game data.

AIDL: handles how the client and the service interact

ViewBinding: eliminates the need of `findViewById()` by generating binding class for each XML layout

DataBinding: in addition to features in ViewBinding, it allows binding variables, invoking methods, in XML while having a longer compile time

How to increase the Notification delivery rate in android?: 

- Use a high-priority notification channel
- Use appropriate notification category
- Avoid sending too many notifications
- Be mindful of battery consumption of the app

`ViewModel` lifecycle in an Android app: 
1. The view requests a `ViewModel` from the `ViewModelProvider`, if it exists skip step 2, if not, create a new one and return it after its initialization
2. A new `ViewModel` is created, it is initialized with any data that is needed to set up its state. This data can come from a variety of sources, such as a database or a remote API.
3. If the view gets destroyed or recreated due to memory constraint or configuration change, the `ViewModel` is retained, it is requested again like in step 1
4. If the view is destroyed because the user closes the app or leave the page, the `ViewModel` gets destroyed too

`WorkManager` provides a way to schedule and execute background tasks, its use-cases are:
1. Sync data
2. Push notifications
3. Upload/Download files
4. Do background cleanup
5. Generate reports
6. Preload data

In `LiveData`, `setValue()` must be called from main thread, while `postValue()` can be used to update from background thread, however if it is invoked multiple times before the main thread executes, only the latest posted value may be observed

Android Support Library: a collection of libraries that provide backward-compatible versions of Android framework APIs as well as additional features and utilities that are not included in the framework. It was introduced to help developers build apps that work across different versions of Android and provide a consistent user experience on all devices.

Multitouch can be handled with `MotionEvent`, `pointerCount` is used to get the number of touch points currently on the screen, and a loop is used to retrieve the position of each touch point using the getX() and getY() methods.

Bundle class is commonly used for passing data between activities, fragments, and other components. While it may be possible to use a simple Map data structure for passing data, there are several reasons why Bundle is a better choice:
1. Type safety: Bundle provides type safety for the data that is being passed. This means that the data is checked to ensure that it conforms to a specific data type, which can help prevent errors and improve code reliability.
2. Serialization: Bundle provides built-in serialization support for many common data types, such as strings, integers, and arrays. This makes it easy to pass data between components, even if the data needs to be persisted across app restarts or device rotations.
3. Compatibility: Bundle is a standard Android class that is widely used across the Android platform. This means that other components, such as system services and third-party libraries, are designed to work with Bundle, which can improve compatibility and reduce the risk of errors.
4. Performance: Bundle is optimized for performance in the Android platform, and provides efficient memory management and serialization algorithms that can improve app performance.

`LiveData`: a class in the Android Jetpack library that is a lifecycle-aware observable data holder class, which means that it allows you to observe changes to a data source and automatically update the UI when the data changes.

`Palette` can extract prominent color of an image

`SpannableString`: allows you to apply formatting, styling, and other decorations to a portion of a text string. It is a subclass of CharSequence, which means that it can be used anywhere that a regular string is used.

`Spannable`: an interface that allows you to apply one or more formatting or styling "spans" to a portion of a text. A Spannable is similar to a SpannableString, but it is more general in that it can be applied to any class that implements the CharSequence interface.

`SharedPreferences` is a mechanism for storing key-value pairs of data in a persistent storage
- `commit()` writes the changes synchronously to the disk, blocking the calling thread until the write operation has completed. Means it can cause delay to UI thread.
- `apply()` writes the changes asynchronously to the disk, without blocking the calling thread. Means that the apply operation is a non-blocking operation and does not cause any delay in the UI thread. The changes made with `apply()` are also guaranteed to be eventually persisted to disk, even if the app is killed before the operation is completed.

9-patch image: a special type of image file format in Android that allows you to define how the image should be stretched or tiled when displayed in different screen sizes or resolutions. A nine-patch image consists of a regular Bitmap with an additional 1-pixel border around it, which defines how the image should be stretched or tiled.

Ways to interact with other apps in Android:
- [Sending the user to another app](https://developer.android.com/training/basics/intents/sending)
- [Get a result from an activity](https://developer.android.com/training/basics/intents/result)
- [Allow other apps to start your activity](https://developer.android.com/training/basics/intents/filters)
- [Package visibility filtering on Android](https://developer.android.com/training/basics/intents/package-visibility)
- [Fulfill common use cases while having limited package visibility](https://developer.android.com/training/basics/intents/package-visibility-use-cases)

`AlarmManager`: a system service in Android that allows you to schedule tasks to run at specific times or intervals. `AlarmManager` is commonly used to schedule tasks that need to run in the background even when the app is not currently running. With `AlarmManager`, you can schedule a task to run at a specific time, such as an alarm clock app that needs to ring at a certain time. You can also schedule a task to run at a specific interval, such as a weather app that needs to update the weather forecast every hour.

`JobScheduler`: a system service introduced in Android 5.0 (API level 21) that allows you to schedule background tasks at specific times or in response to specific events. JobScheduler is an alternative to the `AlarmManager` service, which is used for scheduling tasks at specific times. It is designed to optimize the use of system resources, such as battery and network, by batching jobs together and scheduling them at the most appropriate time. `JobScheduler` allows you to define constraints on when a job should run, such as the device being idle, charging, or connected to a Wi-Fi network.

`Snackbar`: a UI component that displays a short message to the user, typically to provide a brief feedback or notification. A `Snackbar` is similar to a `Toast`, but it provides more options for user interaction and can be customized to suit your app's design.

`Dialog`: a UI component that displays a popup window to the user, typically to provide some kind of information or to request user input. A `Dialog` can be created using the `AlertDialog` class or by creating a custom `Dialog` class.

`DialogFragment`: a subclass of the Fragment class that provides a way to display a `Dialog` as a fragment within an `Activity`. A `DialogFragment` can be used to display a `Dialog` that is persistent across configuration changes, such as screen rotation, and it provides a number of benefits over using a regular `Dialog`.

Key differences between `Dialog` and `DialogFragment`:
- Lifespan and Configuration Changes: A `Dialog` is not tied to the lifecycle of an Activity or Fragment, and it is destroyed when the user dismisses it or when the Activity is destroyed. This means that if you need to display a `Dialog` in response to a configuration change, such as a screen rotation, you need to handle the state saving and restoring yourself. In contrast, a `DialogFragment` is tied to the lifecycle of the host `Activity` or `Fragment`, and it is automatically destroyed and recreated when the configuration changes. This makes it easier to handle state saving and restoring, and it ensures that the `DialogFragment` is displayed correctly after the configuration change.
- Customization: While a `Dialog` is customizable, it can be more difficult to customize than a `DialogFragment`, especially if you need to create a custom layout or behavior. With `DialogFragment`, you can easily create a custom layout, add custom behavior, and respond to user input using methods like `onDialogPositiveClick()` and `onDialogNegativeClick()`.
- Reusability: `DialogFragments` can be reused across multiple `Activities` and `Fragment`s, which can be useful if you need to display the same `Dialog` in multiple places within your app. With `Dialog`s, you need to create a new instance of the `Dialog` each time you want to display it.

`Toast`: a UI component that displays a short message to the user, typically to provide some kind of feedback or notification. A `Toast` is a lightweight component that appears as a floating message on the screen and disappears after a short period of time.

## Architecture

Model: models, local/remove data source and repository. Represents the data and the business logic of the Android Application.

View: mostly Activity, Fragment and XML. Sends the user action to the middleman

MVC:

The view has direct access to the model, the controller handles user interaction and updates the model

- +streamlines code

- -poor scalability

- -difficult unit-testing

- view is not aware of controller, model is exposed to view

- controller: user directly interacts with the controller

MVP:

Presenter: the view calls the view-model via the contract interface, the view-model passes update event to the view via the contract interface

- +easy unit-testing
  
- +better scalability

- -additional view interface

- -coupled view/presenter

- =view has reference to presenter, presenter has reference to view

MVVM:

ViewModel: the view calls the view-model directly, the view-model passes update event to the view via observables e.g. `LiveData` or `StateFlow`

- +no coupled view/viewmodel

- +easy unit-testing

- -observe for each UI component

- -obsessive code

- -requires observer

- =view has reference to VM but VM is not aware of view so VM can be used in multiple views

Why use clean architecture?: modularize the different functions of an application so that each component is separate and can be updated and tested independently

- Domain: contains repeated or complex logic for a view model

- Data: contains data handling logic

- UI: contains UI handling logic

Event-driven architecture:

- +loosely coupled, subscriber does not need to know who sends the event

- +easy to pass simple data for update

- -difficult debugging, harder to trace what subscriber handles the event

## API

OkHttp vs Retrofit: Have to build requests yourself in OkHttp, Retrofit is slower on update because it is based on OkHttp

Gson vs Moshi: Moshi is written in Kotlin, is lighter, uses Kotlin code-gen instead of reflection

Why Moshi uses Kotlin codegen: it allows generating code at compile time instead of at runtime with reflection

OkHttp Interceptor can cache network data so the app is still usable offline, it can also intercept network requests and print them

Multipart request: a type of HTTP request that allows you to send multiple types of data in a single request. This is useful when you need to upload files or send a form with both text and binary data.

gRPC: a framework for communication with Protocol Buffer

- Channel: creates a connection to the server, required by Stub creation

- Stub: calls methods defined in the proto files

gRPC is a new take on an old approach known as RPC (Remote Procedure Call), a form of inter-process communication essentially used for client-server interactions. The client can request to execute a procedure on the server as if it were a normal local procedure call thanks to the stub generated for both client and server.

REST API: there are clients and a server, clients send HTTP request in methods GET/POST/PUT/DELETE to the server, and the server responds in a standard format, usually JSON

JSON: widely used in web development and data exchange between systems. JSON is human-readable and easy to parse and generate using built-in libraries in most programming languages. JSON does not require a schema. However, JSON is less efficient in terms of performance and memory usage than binary formats like FlatBuffers, and requires parsing and validation at runtime.

Protocol Buffers: aka protobuf. It uses a schema to define the data structure, which is written in a language-independent format called the Protocol Buffer Language. The schema is used to generate code in various programming languages to read and write the data in a strongly-typed, type-safe manner. Protocol Buffers supports many data types, including integers, floats, doubles, strings, booleans, and enums. It suffers from the same drawback as protobuf due to lack of human-readable representation.

FlatBuffers: Similar to protobuf. However you don’t need to deserialize the whole data in FlatBuffers before accessing an object. It is faster and more memory efficient than protobuf.

## View

`View`: the basic building blocks of UI elements in Android. `View` is a simple rectangle box which responds to the user’s actions. Superclass for all UI components

`ViewGroup`: a parent to hold children e.g. `LinearLayout` with `TextView` and `Button`

`Canvas`: provides a 2D drawing surface for custom view elements. It is used to draw graphics, text, and other visual elements onto a `View` object.

`Paint`: provides styling and color information for drawing operations on a `Canvas`. `Paint` is used to specify the color, stroke width, style, and other attributes of the lines, shapes, and text that are drawn onto a `Canvas`.

`SurfaceView`: provides a dedicated drawing surface for rendering of graphics and video. Unlike other View types in Android, which are drawn in the normal `View` hierarchy, SurfaceView creates a separate drawing surface in which you can perform custom drawing operations.

How `RecyclerView` works?: The Adapter binds views and then passes them to the Layout Manager, the RecyclerView only allocates fixed numbers of views that fit the screen.  When a view is out of sight it becomes a scrap view and is temporary detached to the recycle pool, when the next items need to be displayed, it is reused by passing new data into the view and then is returned to the viewHolder as "dirty view"

Why use `RecyclerView` over `ListView`?: `ListView` creates as many views as data count in a dataset, with no built-support for animation, only vertical scroll, no support for LayoutManager

`RecyclerView` consists of:

- `Adapter`: binding for a dataset, aware of where each item is located in the dataset

- `LayoutManager`: positions items within the RecyclerView

- `ItemAnimator`: defaults to DefaultItemAnimator

- `ViewHolder`: draws for individual items

`SnapHelper`: a helper class that helps to implement snapping behavior in a `RecyclerView`. Snapping behavior refers to the behavior of automatically scrolling the RecyclerView to the nearest view when the user stops scrolling.

`RecycledViewPool`: can be used to share view pool between `RecyclerView`s, optimizing their performance

The flow on the view happens is when we want to tap the button:
`Activity` -> `dispatchTouchEvent` (LinearLayout) -> `dispatchTouchEvent` (Button) -> `onTouchEvent` (Button). 

Working with touch control:
- When working on touch events we start by clicking a view and removing the gesture (in our case our finger/stylus) then `MotionEvent.ACTION_DOWN` and `MotionEvent.ACTION_UP` is called respectively.
- When the initial touch happens on the `ViewGroup` and after intercepting when it moves to the child, then `MotionEvent.ACTION_CANCEL` gets called on the `ViewGroup` and the touch event dispatches to the children.
- Now, everything depends on `onInterceptTouchEvent()` and its return value. Based on its return value the `dispatchTouchEvent` is dependent, that if returns true the dispatcher is canceled, and if it returns false then the dispatching of the touch event keeps going on until its used. It follows Chain of Responsibility pattern.
- And `onTouchEvent()` if the return value is true, then it means the touch is handled and if it returns false then it means the touch is not handled.

Tips on how to optimize the depth of the `View` tree:
- Use Flat View Hierarchies: Whenever possible, try to use a flat view hierarchy by using fewer nesting levels. This can be achieved by using layout containers such as `ConstraintLayout` or `RelativeLayout`, which allow you to position `Views` relative to each other without the need for nested layouts.
- Use `ViewStub`: `ViewStub` is a lightweight `View` that is used to lazily inflate Views. Instead of including `View`s directly in the layout, you can use `ViewStub` to include `View`s only when they are needed. This can help reduce the depth of the `View` tree and improve performance.
- Use Merge Layouts: Merge layouts allow you to combine multiple Views into a single View, which can help reduce the depth of the View tree. Merge layouts are particularly useful for reusable components that are used in multiple places in the layout hierarchy.
- Optimize Layouts: Avoid using complex layouts or nested layouts that are not necessary. Instead, use simpler layouts and optimize their performance by using techniques such as layout caching, recycling, and minimizing the number of `View`s that need to be measured and laid out.

Mipmaps are used for icons, every resolution of them is used in case the launcher needs to display larger icon

Drawables are used for everything else, only 1 resolution will be used

`TabLayoutMediator`: links TabLayout and ViewPager2, synchronizing positions between them

`dp`: abstract pixel unit scaled based on the dpi of the screen

`sp`: similar to dp except text size preference affects it too

`<plurals>`: XML resource that carries different strings for pluralization

`ConstraintLayout` helpers:

- Chains: a group of views that are linked to each other with bi-directional position constraints, can be used to distribute space between views evenly, eliminating the necessity of `LinearLayout`, they have different modes:

    - `spread`: default mode, it will position the views in the chain at even intervals within the available space
    
    <img src="resources/chain_spread.png" alt="chain spread picture"/>
    
    - `spread_inside`: snaps the outermost views in the chain to the outer edges, and then positions the remaining views in the chain at equal intervals within the available space

    <img src="resources/chain_spread_inside.png" alt="chain spread inside picture"/>

    `layout_constraintHorizontal_weight` / `layout_constraintVertical_weight` can be used to adjust a view's "weight" in its width/height if its `layout_width`/`layout_height` is set to 0 and is in `spread` or `spread_inside` chains

    - `packed`: packs the views together, and then centres the group within the available space, the positioning of the packed views can be further controlled by altering the `bias` value
    
    <img src="resources/chain_packed.png" alt="chain packed picture"/>
    
- `Barrier`: allows you to specify a constraint based on the height/weight of target views, which makes a `Barrier` flexible

- `Guldeline`: specifies a fixed distance in dp or in percent based on the layout's size

- `Flow`: a virtual layout which is able to wrap other views without adding a level to the layout hierarchy

    - wrap modes:

      - `none`: creates a horizontal/vertical chain out of referenced views

      <img src="resources/flow_wrap_none.png" alt="flow wrap none picture"/>

      - `chain`: also creates a chain out of referenced views, but will wrap to the next row/column if items don't fit into one, if there is leftover space in the next row/column, it will distribute the distance between items evenly

      <img src="resources/flow_wrap_chain.png" alt="flow wrap chain picture"/>

      - `aligned`: like `chain`, except it aligns items from left to right

      <img src="resources/flow_wrap_aligned.png" alt="flow wrap aligned picture"/>

- `Layer`: a virtual layout which animates multiple views at once as a single unit

- `Group`: a virtual layout which controls visibility of multiple views

## Compose

### Side effects:

`LaunchedEffect`: run suspend functions in the scope of a composable, only when its key changed

`DisposableEffect`: effects that require cleanup

`SideEffect`: publish Compose state to non-compose code

`rememberCoroutineScope`: obtain a composition-aware scope to launch a coroutine outside a composable

### State:

`remember`: retain the state across recompositions

`rememberSaveable`: on top of what `remember` does, it also utilizes `Bundle savedInstanceState` to retain the state across configuration changes
- However, it requires the data type to be storable by `Bundle` e.g. primitives, strings or `Parcelable`. If not, it is required to have saver/restorer functions. `ViewModel` is preferred over `rememberSaveable`

`produceState`: convert non-Compose state into Compose state

`derivedStateOf`: calculate one or multiple state objects into another state

`snapshotFlow`: convert Compose's State into Flows

`rememberUpdatedState`: reference a value in an effect that shouldn't restart if the value changes, usually used with `LaunchedEffect` or `DisposableEffect`

`Saver`: describes how the object of original class can be simplified and converted into something which is `Saveable`

## Dependency Injection

Why use dependency injection?: 
- easier refactoring
- easier testing
- easier to reuse code
- easier collab
- separate of concern

How DI works?: the service implements the interface, the client depends on the interface, the injector creates the service and injects it into the client

Why use Dagger?:

- easier dependency management

- allowing to pass mocked dependencies from the outside for easier unit testing

- easier scope management

What Are Components In Dagger?: They are a way of telling Dagger 2 what dependencies should be bundled together and made available to a given instance so they can be used.

What Are Modules In Dagger?: installed to that component to allow binding to be accessed

`activityViewModels()`: injects viewmodel shared in the activity

## Performance

ANR: Application Not Responding, occurs when:
- Input dispatching timed out: If your app has not responded to an input event within 5 seconds
- Executing service: If a service declared by your app cannot finish executing `Service.onCreate()` and `Service.onStartCommand()`/`Service.onBind()` within a few seconds.
- `Service.startForeground()` not called: If your app uses `Context.startForegroundService()` to start a new service in the foreground, but the service then does not call `startForeground()` within 5 seconds.
- Broadcast of intent: If a `BroadcastReceiver` hasn't finished executing within a set amount of time. If the app has any activity in the foreground, this timeout is 5 seconds.

ANR can be caused by:
- Long operation on main thread
- Deadlocks
- Infinite loops
- Slow broadcast receivers

Why does the Android App lag?: when GC occurs, your UI stops updating until GC is done. When there is too much done on the main thread

What is Garbage Collection?: reclaiming runtime memory by destroying unreferenced objects

How does Garbage Collection work?: mark roots(objects referenced by static fields, application class, live thread)->mark referenced objects->mark reachable objects->GC unreachable objects

`System.gc()` is used to perform manual garbage collection

Why use Jetpack?: a set of libraries that provide backward compatibility and best practices

R8: trims class/function name, remove unused methods, and merge code only used once

Low Memory Killer priority: empty(unused) processes->background processes(CREATED activity)->service processes->visible processes(STARTED activity)->foreground processes(RESUMED activity)

What to be cautious about while using Bitmap?: Downscale the bitmap or load the lower resolution version of the bitmap to fit into the view, so it does not take up unnecessary memory for no benefit

Memory Leak: unused objects GC is unable to clear

Bitmap Pool enables the reuse of bitmaps, effectively lowering frequency of GC

To handle bitmaps efficiently in Android:
- Use `BitmapFactory.Options` to reduce the memory usage of bitmaps: `BitmapFactory.Options` is a class that provides several options for decoding bitmaps. By setting the `inSampleSize` option to a value greater than 1, you can reduce the memory usage of bitmaps, at the cost of image quality.
- Use the `Bitmap.Config` options to reduce memory usage: The `Bitmap.Config` options allow you to specify the color format and bit depth of the bitmap. By using a lower bit depth or color format, you can reduce the memory usage of bitmaps.
- Load bitmaps asynchronously using a background thread: Loading bitmaps on the main thread can cause the app to freeze and lead to ANR. Instead, you can use a background thread to load bitmaps asynchronously. You can use the `AsyncTask` class or a `ThreadPoolExecutor` to load bitmaps in the background.
- Use a memory cache to reuse bitmaps: Reusing bitmaps can reduce memory usage and improve performance. You can use a memory cache to store bitmaps in memory and reuse them when needed. The `LruCache` class is a useful class for implementing a memory cache in Android.
- Use a disk cache to store bitmaps: If your app loads a large number of bitmaps, you can use a disk cache to store bitmaps on disk. This can help reduce memory usage and improve performance. The `DiskLruCache` library is a useful library for implementing a disk cache in Android.

Image-loading libraries utilize this to load bitmaps more effectively, they also downscale images just enough to fit into a view to avoid excessive memory usage

overdraw: process of drawing the same pixel multiple times in a single frame, which can lead to performance issues and unnecessary battery drain.

Doze: reduces battery usage by putting the device into a deep sleep state when it is not being used, and only allowing essential background processes to run at specific intervals.

App Standby: restricts the background activity of apps that have not been used for a certain amount of time. When an app is in standby mode, it is not allowed to run background processes, receive push notifications, or use the network.

`onTrimMemory()` is a method in the Android Activity and Application classes that is called when the system is low on memory and needs to reclaim resources. The method provides a way for developers to release resources and reduce memory usage in response to system memory pressure. The method takes an integer parameter that indicates the level of memory trimming that the system is requesting:
- TRIM_MEMORY_COMPLETE: the system is in a critical low-memory state, and the application should release as many resources as possible to avoid being killed.
- TRIM_MEMORY_MODERATE: the system is in a moderate low-memory state, and the application should release some resources to avoid being killed.
- TRIM_MEMORY_BACKGROUND: the system is in a low-memory state and the application is running in the background. The application should release resources that are not essential to its operation.
- TRIM_MEMORY_UI_HIDDEN: the application's UI is no longer visible to the user, and the application should release resources that are not needed for background tasks.
- TRIM_MEMORY_RUNNING_MODERATE: the system is in a moderate low-memory state and the application is still running, but the resources it consumes should be reduced.
- TRIM_MEMORY_RUNNING_LOW: the system is in a low-memory state and the application is still running, but the resources it consumes should be reduced.
- TRIM_MEMORY_RUNNING_CRITICAL: the system is in a critical low-memory state, and the application should release as many resources as possible to avoid being killed.

Running blocking tasks in UI thread should be avoided as it will cause UI lag or even ANR, leading to poor user experience

`android:process` in `AndroidManifest` allows an app running in multiple processes

`RecycledViewPool` may improve performance of a nested `RecyclerView` if `RecyclerView`s items are using similar layout

## Lifecycle

Activity:

- `onCreate()`: init activity, only called once through the whole lifecycle

- `onStart()`: when the user can see the screen

- `onResume()`: when the user can interact with the screen

- `onPause()`: when part of the app is visible but in the background

- `onStop()`: when the app is not visible to the user

- `onDestroy()`: activity is no longer used, cleaning up activity

if in `onStart()`, `finish()` is called, `onPause()` and `onStop()` won't be called and will just call `onDestroy()`

In which situations Activity can be in the `onPause()` state and never calls `onStop()`?: 

- Picture-in-Picture mode: the user cannot interact with the activity but it is still visible

- Split screen: when the user has A activity call `onResume()` by interacting with it, B activity has `onPause()` called, but B is still visible

- Another activity started with transparent background: GooglePay purchase screen is actually an Activity with transparent background rather than a Dialog, the previous activity is still visible

`setContentView()` is called in `onCreate()` because `onCreate()` is only called once

`onSavedInstanceState()`: store data before stopping the activity

`onRestoreInstanceState()`: recover the saved state of an activity when the activity is recreated after the destruction

Service:

- `onStartCommand()`: runs when other components request the service to start by `startService()`

- `onBind()`: when other Android components try to connect with the service

- `onCreate()`: setup process when the service is created

- `onDestroy()`: service is no longer used, clean up service

Fragment:

Lifecycle of `viewLifeCycleOwner`: `onViewCreated()`~`onDestroyView()`

Lifecycle of `LifecycleOwner` of the Fragment: `onCreate()`~`onDestroy()`

- `onAttach()`: called when the fragment is added to a `FragmentManager`, and is always called before any lifecycle changes

- `onCreate()`: initialize a fragment, when the fragment is added to a `FragmentManager`

- `onCreateView()`: instantiate UI

- `onViewCreated()`: gives subclasses a chance to initialize themselves once they know their view hierarchy has been completely created

- `onViewStateRestored()`: view states such as checkbox check are restored

- `onStart()`: called when the fragment is visible, tied with `onStart()` of the activity

- `onResume()`: when the fragment is interactable, tied with `onResume()` of the activity

- `onPause()`: when the fragment is no longer interactable, tied with `onPause()` of the activity

- `onStop()`: when the fragment is no longer visible, tied with `onStop()` of the activity

- `onSaveInstanceState()`: saves states of the fragment, so it can be restored to previous state if the process was terminated. Before API 28, this is invoked before `onStop()`

- `onDestroyView()`: when the view `onCreateView()` created has been detached

- `onDestroy()`: when the fragment is no longer used, when the fragment is removed from a `FragmentManager`

- `onDetach()`: called when the fragment is removed from a `FragmentManager`, and is always called after any lifecycle changes

View:

- `onAttachedToWindow()`: when the view is attached to a window

- `onMeasure()`: determine the size of the view

- `onLayout()`: called to assign size for its children if any

- `onDraw()`: called when the view needs to render content

`invalidate()`: rerun from `draw()` process

`requestLayout()`: rerun from `measure()` process

## Debug

adb: a CLI tool used to communicate with a device, can be used to debug, install apps, capture screenshots, record screen and transfer files

App Inspection: can be used to inspect the in-app database and network usage

Profiler: enables tracking CPU, memory and energy usage with events shown in a timeline

Thread dump can be used to identify deadlocks

Heap dump can be used to identify memory issues

StrictMode: a developer tool in Android that helps identify potential performance and threading issues in an application, by detecting violations of certain rules that are known to cause performance problems, such as disk access on the main thread, network access on the main thread, and long-running operations on the UI thread.

How to debug database in an app: Database Inspector can be used to do CRUD and run custom quaries to the database

# Development

Gradle: a build automation tool that is used to build, test, and package Android apps. It provides a flexible and powerful build system that can be used to automate complex build tasks and manage dependencies. It provides:
1. Dependency management: Gradle makes it easy to manage dependencies for a project, including downloading and caching dependencies, resolving conflicts, and specifying custom repositories.
2. Build configuration: Gradle allows developers to configure the build process using a Groovy-based DSL, providing a high degree of flexibility and customization.
3. Task-based build system: Gradle is a task-based build system, which means that it defines a set of tasks that can be run independently or as part of a larger build process. This allows developers to build and test their app incrementally, without having to rebuild the entire app every time.
4. Incremental builds: Gradle supports incremental builds, which means that it only rebuilds the parts of the app that have changed since the last build. This can significantly reduce build times and improve developer productivity.
5. Plugin architecture: Gradle has a plugin architecture that allows developers to extend the build system with custom plugins. This makes it easy to integrate with other tools and technologies, such as testing frameworks, code analysis tools, and continuous integration servers.

CI: build->test->merge CD: automatically deployment

Scrum: iterative development, daily meeting to report and adjust the process

ATDD: tests are written from the user's perspective "is this the result I expected?"

UML Diagram: is used to visualize the flow of a program [Link](https://www.smartdraw.com/uml-diagram/)

Lint: a static code analysis tool that is included with the Android SDK. It is used to analyze the source code of an Android application and identify potential issues, such as performance problems, coding style violations, and security vulnerabilities.

Software Development Lifecycle:

1. Analysis: Gather, clarify and analyze requirements

2. Plan: Architecture decisions, UI design and resource allocation

3. Code: Implement decisions and design

4. Test: Test if the code meets requirements

5. Deploy: Put the project into production

6. Maintenance: New features, bug fixes, monitoring

Firebase: a mobile app development platform that provides a range of tools and services for building Android apps. Such as cloud storage, real-time databases, and authentication, as well as tools for testing, monitoring, and optimizing app performance.
- Firebase Realtime Database: a cloud-hosted NoSQL database that allows developers to store and sync data in real-time across multiple clients

Google/Firebase Analytics can be used to provide matrics on the number of active users, the number of installs, and the number of uninstalls

Staged rollout to control percentage of users to release to, to collect possible issues before fully release

How to change parameters in an app without updating?:
1. download configurations from remote server
2. Firebase Remote Config

Obfuscation: intentionally making code more difficult to understand, by renaming classes, variables, and methods to meaningless or cryptic names. The goal of obfuscation is to make it harder for someone to reverse engineer the code or extract sensitive information from it.

Minification: reduce the size of code by removing unnecessary characters such as whitespace, comments, and unused code. The goal of minification is to reduce the size of the code, which can make the app load faster and use less storage space.

ProGuard: a code obfuscation tool that is used to shrink, optimize, and obfuscate Java bytecode. When used correctly, ProGuard can help to reduce the size of your app and protect your code from reverse engineering. However, it may remove code that is necessary for an app to run, so monitoring after obfuscation is recommended

Multiple APKs: allows developers to create different APKs for the same application, each optimized for different device configurations

Android App Bundle: If an AAB is uploaded to Google Play, it will provide optimized APK for respective device automatically

Dalvik: runtime on JIT(Just In Time) compilation, initially it only compiles part of the bytecode, it compiles more when a part is needed, every time an app starts up, it does the compilation again, inducing decreased battery life and performance, later replaced by ART.

ART: "Android Runtime" runtime on AOT(Ahead Of Time) compilation, it compiles the whole app on installation and stores the code on the disk. Performance and battery issues are solved while occupying more disk space and taking longer to boot and install the app.

ART optimizing profiles: Google collects this usage pattern data to create a code profile, with the profile, ART will compile only the most frequently used part of the code on installation, so the initial run will be faster.

DEX: "Dalvik Executable" is a file format used by the Android operating system to execute compiled code. The DEX format is optimized for Android's virtual machine, the Dalvik Virtual Machine (DVM), which is designed to run on resource-limited devices such as smartphones and tablets.

Android runtime: the DEX file is loaded into memory and executed by either the Dalvik or ART runtime environment. The runtime environment translates the DEX bytecode into machine code that can be executed by the device's processor.

Multidex: a mechanism provided by Android to overcome limitation of 65,536 method references. It allows apps to have multiple DEX files, each containing a subset of the app's method references. When the app is run, the Android runtime loads all the DEX files and combines them into a single Dalvik Virtual Machine (VM) instance.

AAPT2: Android Asset Packaging Tool 2, provided by the Android SDK that is used to package and optimize Android app resources, such as images, layouts, and other assets.

# Programming basics

SOLID:

- S: single purpose per class

- O: open to extension but closed to modification

- L: replacing a superclass with its subclass shouldn't break any functions

- I: depending on the interface with needed functions rather than a concrete class with many functions, do not make functions useless to the client visible to it.

- D: high-level classes shouldn't depend on low-level classes, high-level class depends on an interface to avoid changing along with low-level classes

Interface: cannot have fields, can implement multiple interfaces, functions cannot be final. Used when functions are expected on unrelated classes when no changes are expected when 
ing small bits of functionality, can be inherited from another interface. "What can the object do"

Abstract class: can only inherit 1 abstract class, has constructors. Used when a base class is needed, when additional features are expected, inherence does not break superclass functions "What this object is"

Serialization: a process of translating a data structure or object state into a format that can be stored or transmitted

Serializable: easy to use and is built into Java, as it requires no additional code to be written, but it can be slower and less memory-efficient than Parcelable. Serializable also creates a lot of temporary objects during the serialization process, which can impact performance.

Parcelable: Android-specific interface that allows an object to be flattened into a parcel and reconstructed later. Parcelable is faster and more memory-efficient than Serializable, as it creates fewer temporary objects during the serialization process. However, Parcelable requires more boilerplate code to be written compared to Serializable. Fortunately, with `@Parcelize` annotation in kotlinx, `Parcelable` methods will be generated automatically

Both `Serializable` and `Parcelable` are used to transfer data between screens, usually via `Bundle`

Strong reference: as long as there is a strong reference to an object, it will not be eligible for garbage collection

Weak reference: cleared by the garbage collector as soon as there are no more strong references to the object

Soft reference: cleared by the garbage collector only when the JVM is running low on memory. Soft references are typically used to implement memory-sensitive caches

Phantom reference: enqueued by the garbage collector after the object to which it refers has been finalized, but before the object's memory has been reclaimed. Phantom references are typically used to perform post-mortem cleanup operations on objects, such as removing their associated resources, after they have been finalized.

OOP: a concept about classes and objects which can be done in inheritance, polymorphism, abstraction, and encapsulation

Polymorphism: same method name can behave differently in different classes

Overloading: passing different parameters will give different behaviors

Abstraction: exposes only what the object does e.g. abstract class and interface

Encapsulation: hides away implementation detail e.g. private, protected

Inheritance: inherit behaviors and info of the superclass

It is a bad idea to call an abstract method from a constructor because the implementation of the method is not yet known, leading to unexpected behavior

Anonymous class enables quick implementation and definitation of an interface, or extending a class without naming said class

Instantiation is the process of creating a new object

Initialization is the process of setting the initial values of an object's instance variables

`PriorityQueue` allows elements to be inserted with an associated priority, and provides operations to remove the element with the highest priority, used in sorting, task scheduling, and event processing

Generics: provide a way to write generic classes and methods that can work with different types, while still providing compile-time type safety

## Kotlin/Java

inline function: inlined functions will not be actual functions in bytecode, instead the piece of code is part of the function used inline function, and will not be able to access private members/methods of your enclosing class. You will need to make those members/methods internal and then annotate them with `@PublishedApi`. Will be able to return from the lambda which in turn will return from the calling function.

- `crossinline`: disallow return in lambda

- `noinline`: `noinline` lambdas do not support non-local control flow, i.e you won’t be able to propagate your return to the calling function.

`@JvmStatic`: marks the function as static in java, so you don't have to call that function by `AppUtils.INSTANCE.install()`

`@JvmOverloads`: java does not support default parameters, use this annotation to tell the compiler to create overloads for java

`@JvmField`: tells the compiler not to generate getter/setter for the field

Reflection is the ability to make modifications at runtime by making use of introspection.

`Integer` vs `int`: `Integer` is a wrapper class of int which has utility functions to manipulate `int`, while `int` is a primitive type

You can implement another interface inside an interface

Auto/UnBoxing: auto/unwrapping primitive types to wrapper type

`equals()` vs `==`: `==` compares reference in Java, `equals()` compares value based on how the class implements

`==` vs `===` in Kotlin: `==` checks `equals()`, `===` checks reference

`Pair<T1, T2>`: pair 2 data together so no need to declare a new data class for storing them

`Triple<T1,T2,T3>`: pair 3 data

`Array` in Kotlin: has fixed size, has better performance and is mutable so values can be changed, it is invariant

`List` in Kotlin: immutable by default unless created as `MutableList`, has to add/remove method to manipulate list size, has better scalability, it is covariant

label in Kotlin: used to declare which loop to break/continue in a for loop

Overloading: methods have the same name but different signatures e.g. parameters

`data class`: some utility functions are created by Kotlin if it is marked data class:

- `toString()`

- `equals()`/`hashCode()`: only evaluates properties from the primary constructor

- `copy()`: only copies properties from the primary constructor

- `componentN()`: corresponding to the properties in their order of declaration. The compiler only uses the properties defined inside the primary constructor for the automatically generated functions

`sealed class/interface`: forces to add remaining branches while the type-checked `sealed class/interface` object is in `when()`

`let()`: inputs object as a receiver, object as a parameter in lambda, returns the lambda result

`run()`: inputs object as a receiver, object as a receiver in lambda, returns the lambda result

`also()`: inputs object as a receiver, object as a parameter in lambda, returns the object

`apply()`: inputs object as a receiver, object as a receiver in lambda, returns the object

`with()`: inputs object as a parameter, object as a receiver in lambda returns the lambda result

Static block in a class is run before invoking a static method, or before instantiating the first object of the class, it is run only once

Static methods cannot be overridden

`throw` manually throws an exception

`throws` in Java or `@Throws` in Kotlin indicates that a function may throw an exception

`try-catch` is used to gracefully recover from an exception

`finally` is invoked whether an exception is handled or not in `try-catch`

`finalize()` is invoked to perform cleaning up before being garbage collected, deprecated in Java 9 as it can lead to unexpected behavior

`String.intern()` returns a canonical representation of a String object from the string pool

Advantages of making an object final:
- ensuring it is never changed after initialization
- making it thread safe
- allows JVM to optimize it

`transient` excludes said field from being serialized, such as conditental data, cache field or non-serializable field

A checked exception must be declared in a method or constructor's throws clause. When a checked exception is thrown, the calling method must either handle the exception or declare it in its own throws clause. e.g. `IOException`, `SQLException`, `ClassNotFoundException`.

An unchecked exception, also known as a runtime exception does not need to be declared in a method or constructor's throws clause. Unchecked exceptions are usually caused by programming errors, such as null pointer dereferences, division by zero, or out-of-bounds array accesses. Examples of unchecked exceptions in Java include `NullPointerException`, `ArithmeticException`, and `ArrayIndexOutOfBoundsException`.

`volatile` ensure that changes to a shared variable's value will be immediately visible to all threads that access the variable

`synchronized` ensures that only one thread can execute a block of code or access an object's state at a time, preventing race conditions and other synchronization problems

`ThreadPoolExecutor` is for managing and executing tasks in parallel

Java memory model defines the rules and guarantees for how threads interact with shared memory in a Java program. It specifies how threads can access shared data and how changes made by one thread become visible to other threads. It guarantees 2 contracts:
- Visibility: Changes made by one thread to shared memory are guaranteed to be visible to other threads that access the same memory.
- Atomicity: Operations on shared memory are guaranteed to be executed atomically, meaning that they are either completely executed or not executed at all.

Java memory model defines two types of memory:
- Heap is a shared memory region where all objects are allocated. The Heap is managed by the garbage collector and is used to store objects that are created at runtime. All threads in a Java program share the same Heap, and objects on the Heap can be accessed by multiple threads.
- Stack is a memory region where each thread has its own stack frame. A stack frame contains information about method calls, local variables, and other data specific to a particular thread. When a thread calls a method, a new stack frame is created for that method. When the method completes, the stack frame is removed. The Stack is used to store primitive types and object references, and is not shared between threads.

`Iterator`: an interface that is used to traverse through collections of objects, such as `ArrayList`, `LinkedList`, and `HashSet`. The `Iterator` provides a way to iterate over the collection and access its elements one by one without exposing the underlying data structure of the collection.

Kapt: generate annotation for Kotlin

Annotation: allows you to add metadata, or information about code, to your program. Annotations provide a way to associate information with a program element, such as a class, method, variable, or parameter, without affecting its runtime behavior.

Annotation processing: allows compile-time code generation for annotated classes/methods, in order to simpify development

Annotation retention: specifies how long an annotation should be retained, or kept, by the JVM, available retention policies are:
- `RetentionPolicy.SOURCE`: Annotations with this retention policy are only retained in the source code and are discarded by the compiler during the compilation process. They are not available at runtime.
- `RetentionPolicy.CLASS`: Annotations with this retention policy are retained in the compiled class file but are not available at runtime. This is the default retention policy if no retention policy is specified.
- `RetentionPolicy.RUNTIME`: Annotations with this retention policy are retained in the compiled class file and are available at runtime through reflection. This is the only retention policy that allows you to access annotations at runtime.

Target element type: specifies the types of elements that an annotation can be applied to. There are several target element types available:
- `ElementType.TYPE`: can be applied to classes, interfaces, enums, and annotations.
- `ElementType.FIELD`: can be applied to fields, including enum constants.
- `ElementType.METHOD`: can be applied to methods.
- `ElementType.PARAMETER`: can be applied to parameters of a method or constructor.
- `ElementType.CONSTRUCTOR`: can be applied to constructors.
- `ElementType.LOCAL_VARIABLE`: can be applied to local variables.
- `ElementType.ANNOTATION_TYPE`: can be applied to annotations.
- `ElementType.PACKAGE`: can be applied to packages.
- `ElementType.TYPE_PARAMETER`: can be applied to the type parameter of a generic class, interface, method, or constructor.
- `ElementType.TYPE_USE`: can be applied to any type use, such as casting, instanceof checks, and method references.


Creating an annotation class in Kotlin: 
1. define a new annotation class e.g. `annotation class MyAnnotation(val value: String)`
2. `@Retention` and `@Target` annotations are used to specify the annotation retention policy and target element type, respectively.
```
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MyAnnotation(val value: String)
```
3. use the custom annotation
```
@MyAnnotation("Hello, World!")
class MyClass
```
4. retrieve the annotation and its value from `MyClass` at runtime
```
val annotation = MyClass::class.annotations.find { it.annotationClass == MyAnnotation::class }
val value = (annotation as? MyAnnotation)?.value
```
ArrayMap: an Android-specific data structure that implements the Map interface and uses arrays to store key-value pairs. ArrayMap provides similar performance to HashMap for small and medium-sized data sets, but is more memory efficient for larger data sets.

SparseArray an Android-specific data structure that implements the Map interface and uses arrays to store key-value pairs, but is specialized for integer keys. SparseArray is optimized for memory usage and is more memory efficient than HashMap and ArrayMap for data sets with integer keys. SparseArray provides constant-time performance for most operations, but may be slower than HashMap and ArrayMap for data sets with non-integer keys.

In Kotlin, the primary constructor is called first, then fields or `init{}` depending on their code positions, then the secondary constructor.

### Coroutines

Kotlin Coroutines: enables writing in synchronous way, while the code actually runs asynchronously and concurrently

`CoroutineScope`: the lifecycle of this coroutine

`Job`: its main responsibility is to manage coroutine cancellation.

- `join()`: blocks the execution of the rest of the coroutine code before this job is finished

`CoroutineContext`: provides the required execution environment to run code asynchronously, consists of below:

- `CoroutineDispatcher`: defines thread pools to run in.

- `CoroutineName`

- `CoroutineExceptionHandler`

- `Job`

`CoroutineBuilders`: helps create coroutines, can be called in non-suspending functions

`Coroutines.async()`: used when one needs to wait for the result, creates a `Deferred<T>` object.

`Deferred.await()`: waits to get the result

`Coroutines.launch()`: is set and forget.

`withContext()`: switch thread to another in the coroutine scope.

`supervisorScope`/`supervisorJob`: if 1 thread fails it will continue to run.

`GlobalScope`: cannot be canceled

`Mutex().withLock()`: protect all modifications of the shared state with a critical section that is never executed concurrently

`Semaphore`: a synchronization mechanism that allows a fixed number of threads or coroutines to access a shared resource simultaneously. It maintains a count of the number of permits available, and each thread or coroutine that wants to access the resource has to acquire a permit before proceeding. The permits are released back to the semaphore when the resource is no longer needed, allowing other threads or coroutines to acquire them.

`CoroutineExceptionHandler` only catches unhandled exception, that is, if the block already handles the exception, it will not be triggered

`CoroutineExceptionHandler` has no effect on `async()` because the builder catches and represents the exception in the `Deferred` object

`CoroutineExceptionHandler` does not catch exception if it is installed in an inner coroutine

Uncaught exceptions will always be thrown regardless of the kind of `Job` you use.

Remember that a `SupervisorJob` only works as described when it’s part of a scope: either created using `supervisorScope` or `CoroutineScope(SupervisorJob())`. Passing a `SupervisorJob` as a parameter of a coroutine builder will not have the desired effect you would’ve thought for cancellation.

With `launch`, exceptions will be thrown as soon as they happen

When `async` is used as a root coroutine (coroutines that are a direct child of a CoroutineScope instance or supervisorScope), exceptions are not thrown automatically, instead, they’re thrown when you call `.await()`.

Exceptions thrown in a `coroutineScope` builder or in coroutines created by other coroutines won’t be caught in a try/catch!

### Flow

A flow consists of 3 components: flow builder, operator, collector

cold flow: only starts emitting values when it is subscribed, values are not shared among other subscribers either

hot flow: starts emitting values no matter if it is subscribed or not, values are shared among subscribers

Why use StateFlow (over LiveData)?: has initial value and hence can be non-nullable, LiveData is dependant on the Android framework and is run on the main thread while StateFlow is multiplatform

Why use Flow (over Rx)?: has multiplatform support, suspending, enforced to be in scope, nullability support, and easier to write custom flows

`SharingStarted.WhileSubscribed()` cancels the upstream flow when there are no collectors

`viewModelScope` launches on the Main dispatcher by default

`Flow.tryEmit()`: tries to emit a value without suspending, can only return false if BufferOverflow strategy is suspended and subscribers are collecting shared flow

`StateFlow` vs `SharedFlow`: Data rendered by the `StateFlow` (Text Composable) gets preserved after rotation. On the other hand, when using `SharedFlow`, the `Toast` does not get shown again after screen rotation. `StateFlow` supports `StateFlow.value`, but does not collect repeated values, does not support replaying beyond the latest value, and requires an initial value.

Flow builders:

- `callbackFlow {}`: turns a callback listener into a Flow

    - `trySend()`: posts callback data to Flow, will drop data not collected if the next data is sent

    - `send()`: similiar to `trySend()` but is a suspend function

    - `trySendBlocking()`: will block the newest emission until the collector has collected all previous emissions

    - `awaitClose()`: unregister the callback listener when the Flow is closed
 
- `flowOf()`: turns a set of objects into a Flow

- `asFlow()`: turns a type into a Flow

- `channelFlow{}`: creates a Flow with the elements emitted using `send()` provided by the builder itself

- `MutableSharedFlow`

- `MutableStateFlow`

Flow intermediate operators:

Functions that are applied to the upstream flow or flows and return a downstream flow where further operators can be applied to.

- `transform()`: can emit arbitrary values many times

- `map()`: return result of transformed value

- `conflate()`: skip the emission if the collector can't process it in time

- `buffer()`: buffer the emission so the collector can process all while the source doesn't need to wait for the collector

- `take()`: cancel the execution of the flow when the corresponding limit is reached

- `zip()`: combines the corresponding values of two flows

- `combine()`: recompute whenever any of the upstream flows emit a value

- `flatMapLatest()`: cancel and recreate flow in the lambda if the original flow changes

- `flatMapConcat()`: concatenate original emitted flow execution

- `flatMapMerge()`: execute all emitted flow concurrently

- `debounce()`: emission is ignored until defined time elapsed

- `retry()`: handles exception by retrying until the retry limit is reached, then it will pass the exception to downstream

- `filter()`: filters out unmatched emission

- `distinctUntilChanged()`: identical emission is dismissed
 
- `flowOn()`: specifies what `Dispatcher` the Flow should be run on
    - `Dispatcher`s: helps decide which thread should a task run on
        - `Default`: for CPU-intensive tasks
        - `IO`: for network or disk tasks
        - `Main`: for UI tasks
     
- `catch()`: handles exceptions

 Flow terminal operators:

 Applied to the upstream flow and trigger execution of all operations

 - `collect()`: accepts the given collector and emits values into it

 - `collectLatest()`: when the original flow emits a new value then the action block for the previous value is canceled

 - `fold()`: accumulates value starting with initial value and applying operation current accumulator value and each element. Returns initial value if the collection was empty, can change the result type

 - `reduce()`: accumulates value starting with the first element and applying the operation to the current accumulator value and each element. Throws `NoSuchElementException` if the flow was empty.

 - `launchIn()`: launches the collection of the given flow in the scope.  It is a shorthand for `scope.launch { flow.collect() }`

### String

String concatenation: `String`s are immutable in Java, so when a `String` is concatenated, it creates a new `String` and discards the old one

`StringBuilder`: mutable, asynchronous thus unsuitable for multi-threaded conditions, but is the fastest. It will automatically expand its capacity to fit added chars

`StringBuffer`: mutable, synchronous thus suitable for multi-threaded conditions, but is slower than StringBuilder

How is `String` implemented?: it is a wrapper of an array of chars which is final

Why is `String` immutable?: 
- prevent manipulation of sensitive data
- thread-safety
- can be cached in string constant pool
- more performant than being mutable

### List function

- `associateBy()`: turns to a map

- `partition()`: turns to a pair by a predicate

- `flatMap()` is used to combining all the items of lists into one list

- `map()` is used to transform a list based on certain conditions


# Version Control

`git fetch`: brings my local copy of the remote repository up to date.

`git pull`: brings the changes in the remote repository to where I keep my own code.

# Design patterns

## Creational

- Singleton: single instance wherever it is accessed

- Factory: dedicate the creation method to the superclass, while allowing subclasses to override the method, used when a superclass has multiple subclasses, and the client does not need to care about the used subclass

- Abstract Factory: creates families of related objects without instantiating directly, needing super factory for related factories

- Prototype: clones object instead of constructing a new one, used when construction is time-consuming

- Builder: input parameters one by one (build by steps), used to avoid instantiation of a class with many parameters, writing tests

## Structural

- Adapter: lets 2 classes work with each other without modifying their codes, done by interface conversion

- Facade: simple interface to hide large code base

- Composite: The component manages Leaves uniformly

- Proxy: a proxy disguises itself as the actual service, enabling it to take control of the service without the client knowing, you can also replace a proxy easily because it implements the same interface as the service does, used when the duplication of the target object is expensive e.g. database

## Behavioral

- Strategy: interface to handle different use cases

- State: state is an extension of the strategy pattern, it allows states to manipulate the context of other state

- Observer: callback to its subscribers when value changes, used to achieve separation of concern

- Mediator: this pattern forces components to collaborate via the mediator, components extend the mediator interface, while the concrete mediator object holds references to all components, components are not aware of each other, used to reduce dependencies

# Test

3A: Arrange, Act, Assert

Why unit test?: identify defects early, enable code reuse, improve code readability, reduce refactor cost, quickly get feedback

Fake object: lightweight and usually frameworkless implementation of the real-world functions for testing

Mock object: object with preconfigured behaviors that is expected to be run defined times in a test

Robolectric: a testing framework for Android that allows developers to write unit tests that run on the JVM instead of on a device or emulator. The framework provides a simulated Android environment that allows developers to test their code in a controlled and isolated environment, enabling faster and more efficient testing.

Espresso: a testing framework for Android that allows writeing automated tests for UI testing. Espresso is designed to help developers test the user interface of their applications on real Android devices or emulators.

`RuleChain` is needed when multiple rules are in a test class e.g. `@get:Rule val rules = RuleChain.outerRule(ruleA).around(ruleB)`

# Network

OSI Model: Describes how each communication protocol works together to enable communication between 2 devices

- Layer 7 Application Layer: resource sharing or remote file access between applications e.g. HTTP

- Layer 6 Presentation Layer: translation of data e.g. encoding, data compression and encryption

- Layer 5 Session Layer: managing communication sessions

- Layer 4 Transport Layer: transmission of data using communication protocols e.g. TCP

- Layer 3 Network Layer: packet route selection and forwarding e.g. IP

- Layer 2 Data Link Layer: establishes and terminates a connection between two physically-connected nodes on a network

- Layer 1 Physical Layer: transmission of bit streams over a physical medium

# Computer Science

Program: executables stored in the storage

Process: executables running in the system

Thread: processes divided into multiple work units

Coroutine: switch to another thread while a processing thread is locking

Multi-tasking: do one more thing at a time

Multi-programming: load multiple programs into the system

Multi-threading: fast switching between threads

Synchronous: only process 1 task at a time

Asynchronous: can have more than 1 task ongoing at a time

Concurrent: 2 or more tasks are processed by 1 processor

Parallel: a task is broken into multiple subtasks processed by multiple processors

Why floating numbers are inaccurate: because they can only hold this many bits after floating points, they will never accurately present an irrational number.

# DB

An organized collection of data that is stored electronically

`||` is a string concatenate operator. Think of it as `+` in Java String.

# LeetCode

BFS has no backtracking and uses a queue.

DFS uses the stack.

Why does accessing `HashMap` have a time complexity of O(1)?: traverses between hash strings instead of elements in the map.

Implementation of `PriorityQueue` defaults to min heap unless `compareByDescending()` is used.

# System Design

Steps of system designing:

1. Define the problem: System design problem is usually ambiguous, so communicate with your interviewers.

2. Design high-level: Start from overviewing the whole system, then go into the detail.

3. Dive deep: Highlight the core components and constraints.

4. Find bottlenecks: Solve scalability problem

5. Summarize and answer questions: Summarize design choices and elaborate if the choices justify for the tradeoffs

API notification methods:

- Push notification: pushes a notification to the client from a 3rd-party service
- Short polling: make a request at regular period
- Long polling: make a request that the server holds open for a period until an update event, or the request timeout
- Server-Sent Events: the server monodirectionally notifies events over a stream HTTP connection
- WebSocket: holds a bidirectional connection which is able to transfer data as well

HTTP: Primary protocol used to transfer data between a web server and a client.

HTTPS: Secured version of HTTP

TCP/IP: Connects devices over the internet for data transmission.

Load Balancing: Distribute workloads to multiple servers, so that the addition of server is possible.

Content Delivery Networks: A network of servers that delivers content to the user based on their location, the purpose is to maintain content availability and to improve user experience (reduced latency).

CAP Theorem: A principle that states a distributed database system cannot guarantee all three of the following properties simultaneously.

- Consistency

- Availability

- Partition tolerance

Caching: Store frequently accessed data in a temporary storage location to improve time spent on retrieving data.

Horizontal scaling: aka scaling out, increasing the capacity of a system by adding more machines or nodes to the system. It is commonly used in distributed systems.
- Pros: less expensive, can provide better fault tolerance and availability
- Cons: synchronization between nodes increases overhead and complexity

Vertical scaling: aka scaling up, increasing the capacity of a system by upgrading the hardware or software of an existing machine. It is commonly used in systems that have a single point of failure, such as a database or a message queue.
- Pros: easier to manage, possible better performance
- Cons: scaling has limit, more expensive

Microservices: a collection of small, independent services that communicate with each other through APIs. Each microservice is designed to perform a specific business function, and can be developed, deployed, and scaled independently of the other services in the system. Advantages include:
- Agility and flexibility: Microservices allow for more flexibility in software development and deployment, since each service can be developed, tested, and deployed independently of the others. This makes it easier to add new features, fix bugs, and scale the system as needed.
- Scalability and resilience: Microservices can be scaled horizontally to handle increased traffic or workload, and can be designed to be fault-tolerant and resilient to failures. This can help ensure that the system remains available and responsive even in the face of hardware or software failures.
- Technology diversity: Microservices can be developed and deployed using different technologies and programming languages, which can be better suited to the specific needs and requirements of each service. This can allow for more innovation and experimentation in software development.
- Team autonomy: Microservices can be developed and maintained by small, cross-functional teams that have ownership and autonomy over their specific services. This can enable faster and more efficient decision-making, and can help avoid dependencies and bottlenecks in the development process.
